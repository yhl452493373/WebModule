package com.h3w.webmodule.aspect;

import com.alibaba.fastjson.JSON;
import com.h3w.webmodule.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(log)")
    public Object invoked(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        //Log所在注解方法执行前
        if (!log.afterInvocation()) {
            recordLog(joinPoint, log);
        }
        Object object = joinPoint.proceed();
        //Log所在注解方法执行后
        if (log.afterInvocation()) {
            recordLog(joinPoint, log);
        }
        return object;
    }

    //todo 在这里进行记录操作日志的处理
    private void recordLog(ProceedingJoinPoint joinPoint, Log log) {
        //todo 获取登录后的用户名
        String username = "用户名,自行获取登录后的用户名";
        String operation = log.operation();
        String objectId = log.objectId();
        String object = log.object();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        if (!"".equals(objectId) || !"".equals(object)) {
            String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
            Object[] values = joinPoint.getArgs();
            if (parameterNames != null) {
                ExpressionParser parser = new SpelExpressionParser();
                EvaluationContext context = new StandardEvaluationContext();
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i], values[i]);
                }
                if (!"".equals(objectId))
                    objectId = (String) parser.parseExpression(objectId).getValue(context);
                if (!"".equals(object))
                    object = JSON.toJSONString(parser.parseExpression(object).getValue(context));
            }
        }
        if (!"".equals(objectId) || !"".equals(object)) {
            if (!"".equals(objectId)) {
                if (logger.isInfoEnabled())
                    logger.info(simpleDateFormat.format(new Date()) + ":用户<" + username + "]> - <" + operation + ">,对象id<" + objectId + ">");
            } else {
                if (logger.isInfoEnabled())
                    logger.info(simpleDateFormat.format(new Date()) + ":用户<" + username + "]> - <" + operation + ">,对象<" + object + ">");
            }
        } else {
            if (logger.isInfoEnabled())
                logger.info(simpleDateFormat.format(new Date()) + ":用户<" + username + "]> - <" + operation + ">");
        }
    }
}
