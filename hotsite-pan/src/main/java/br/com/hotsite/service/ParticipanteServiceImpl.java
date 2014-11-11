package br.com.hotsite.service;

import br.com.hotsite.dao.UsuarioDao;
import br.com.hotsite.modelo.Usuario;

public class ParticipanteServiceImpl implements UsuarioService{

	@Override
	public Usuario login(String cpf) {
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.findByCpf(cpf);
	}

	@Override
	public void salva(Usuario usuario) {
		UsuarioDao dao = new UsuarioDao();
		dao.persiste(usuario);
	}

}
