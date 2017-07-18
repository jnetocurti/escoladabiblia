Sandbox('*', function(box) {
	
	editar = function(id) {
		
		box.post(context + 'alunos-comuns/editar', id.toString(), {
			success : function(data) {
				
				box.set('#id', data.id);
				box.set('#nome', data.nome);
				box.check('#batizado', data.batizado);
				box.set('#observacao', data.observacao);
				box.set('#nascimento', data.dataNascimento);
				box.check('#possuiBiblia', data.possuiBiblia);
				box.checkRadio('input:radio[name=sexo]', data.sexo);
				box.check('#frequentouIgreja', data.frequentouIgreja);
				
				box.set('#id-endereco', data.endereco.id);
				box.set('#cep', data.endereco.cep);
				box.set('#logradouro', data.endereco.logradouro);
				box.set('#numero', data.endereco.numero);
				box.set('#bairro', data.endereco.bairro);
				box.set('#complemento', data.endereco.complemento);
				box.set('#cidade', data.endereco.cidade);
				box.set('#estado', data.endereco.estado.id);
				
				box.switchArea('.area-grid-alunos', '.area-form-alunos');
			}
		});
	};
	
	(function loadIfExistsPathParamIdAluno(){
		var id = /(alunos-comuns\/?)(\d*)/g.exec(window.location.href)[2];
		if (id) {
			box.showMsg({type: 'SUCCESS', message: 'Dados do aluno atualizados com sucesso!'});
			$('.grid-alunos').bootgrid('search', box.get('#alunoPath'));
		}
	})();
	
});
