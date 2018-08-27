package com.tech.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tech.model.Tenant;
import com.tech.repository.TenantRepository;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(getClass());
	{
		logger.debug("Creating TenantInterceptor interceptor");
	}

	@Autowired
	TenantRepository tenantRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String tenantUuid = request.getHeader("tenant-uuid");
		Tenant tenant = tenantRepository.findBySchemaName(tenantUuid);
		String defSchma = "multiten";
		if (tenant == null)
			TenantContext.setTenantSchema(defSchma);
		else {
			if (tenant != null) {
				TenantContext.setTenantSchema(tenant.getSchemaName());
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("Clear TenantContext: {}", TenantContext.getTenantSchema());
		TenantContext.setTenantSchema(null);
	}

}
