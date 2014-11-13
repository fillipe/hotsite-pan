package br.com.hotsite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.hotsite.managedbean.LoginBean;
import br.com.hotsite.managedbean.UsuarioBean;

public class ParticipanteFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		LoginBean loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("login");
		
		String contextPath = ((HttpServletRequest) request).getContextPath();
		String paginaInstitucional = "/pages/participante/paginaInstitucional1.xhtml";
		String paginaLogin = "/pages/login.xhtml";

		if (loginBean == null || !loginBean.isLoggedIn()) {
			((HttpServletResponse) response).sendRedirect(contextPath + paginaLogin);
		} else if (loginBean != null && loginBean.isPresencaConfirmada()
				&& !(contextPath + paginaInstitucional).equals(((HttpServletRequest) request).getRequestURI())) {
			((HttpServletResponse) response).sendRedirect(contextPath + paginaInstitucional);
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
