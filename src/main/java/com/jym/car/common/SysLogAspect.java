package com.jym.car.common;

import com.alibaba.fastjson.JSON;
import com.jym.car.annotation.MyLog;
import com.jym.car.mapper.JymLogMapper;
import com.jym.car.model.entity.JymLog;
import com.jym.car.model.entity.LoginUser;
import com.jym.car.util.IpUtils;


import com.jym.car.util.UserThreadLocal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;



@Aspect
@Component
public class SysLogAspect {

    private static final Logger log = LogManager.getLogger(SysLogAspect.class);


    @Autowired
    private JymLogMapper jymLogMapper;

    /**
     * 定义切点 @Pointcut
     *
     */
    @Pointcut("@annotation(com.jym.car.annotation.MyLog)")
    public void logPoinCut() {
    }


    @Around("logPoinCut()")
    public Object saveOutLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("---------------接口日志记录---------------");
        //用于保存日志
        JymLog jymLog = new JymLog();
        // 这里是获得当前请求的request
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String requestURI = request.getRequestURI().toString();
        jymLog.setUri(requestURI);
        // 客户端ip
        String ip = IpUtils.getIpAddr(request);
        jymLog.setLoginIp(ip);
        //通过反射机制获取切入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作--方法上的Log的值
        MyLog myLog = method.getAnnotation(MyLog.class);
        //操作人账号、姓名
        LoginUser loginUser = UserThreadLocal.get();
        jymLog.setUserId(loginUser.getUser().getId().toString());
        jymLog.setUsername(loginUser.getUsername());
        if (myLog != null) {
            //保存操作事件
            String operation = myLog.operation();
            jymLog.setOperation(operation);
            //保存日志类型
            int type = myLog.type();
            jymLog.setType(type);
            log.info("operation={},type={},操作人id={},操作人={}",operation,type,jymLog.getUserId(),jymLog.getUsername());
        }

        //UUID
        String id = UUID.randomUUID().toString().replace("-", "");
        jymLog.setId(id);
        jymLogMapper.insert(jymLog);
        Object[] args = joinPoint.getArgs();
        List<Object> list = Arrays.asList(args);
        log.info("入参=" + JSON.toJSONString(list));
        Object proceed = null;
        proceed = joinPoint.proceed();
        log.info("出参=" + JSON.toJSONString(proceed));
        return proceed;
    }

    @AfterThrowing(throwing="ex",pointcut = "logPoinCut()")
    public void afterThrowing(Throwable ex) {
        log.info("异常" + ex);
    }

}
