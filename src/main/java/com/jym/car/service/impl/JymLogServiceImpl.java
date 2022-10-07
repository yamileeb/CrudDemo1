package com.jym.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jym.car.mapper.JymLogMapper;
import com.jym.car.model.entity.JymLog;
import com.jym.car.service.JymLogService;
import org.springframework.stereotype.Service;

/**
 * @author lb
 */
@Service
public class JymLogServiceImpl extends ServiceImpl<JymLogMapper, JymLog> implements JymLogService {
}
