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
		UsuarioBean usuarioBean = (UsuarioBean) ((HttpServletRequest) request).getSession().getAttribute("usuarioBean");
		
		String contextPath = ((HttpServletRequest) request).getContextPath();

		if (loginBean == null || !loginBean.isLoggedIn()) {
			((HttpServletResponse) response).sendRedirect(contextPath + "/pages/login.xhtml");
		} else if (usuarioBean != null 
				&& usuarioBean.getUsuario() != null
				&& usuarioBean.getUsuario().getPresencaConfirmada() != null
				&& usuarioBean.getUsuario().getPresencaConfirmada()) {
			((HttpServletResponse) response).sendRedirect(contextPath + "/pages/participante/paginaInstitucional1.xhtml");
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
