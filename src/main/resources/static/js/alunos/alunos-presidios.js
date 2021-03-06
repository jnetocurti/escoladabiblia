Sandbox('*', function(box) {
	
	editar = function(id) {
		
		box.post(context + 'alunos-presidios/editar', id.toString(), {
			success : function(data) {
				
				box.set('#id,#id-liberto', data.id);
				box.set('#nome,.nome-liberto', data.nome);
				box.check('#batizado', data.batizado);
				box.set('#observacao,#observacao-liberto', data.observacao);
				box.set('#nascimento', data.dataNascimento);
				box.check('#possuiBiblia', data.possuiBiblia);
				box.set('#cela,#cela-liberto', getCaracterizacao(data).cela);
				box.set('#raio,#raio-liberto', getCaracterizacao(data).raio);
				box.set('#presidio', getPresidio(data).id || '');
				box.checkRadio('input:radio[name=sexo]', data.sexo);
				box.check('#frequentouIgreja', data.frequentouIgreja);
				box.set('#matricula,#matricula-liberto', getCaracterizacao(data).matricula);
				box.set('#complemento', getCaracterizacao(data).complemento);
				box.set('#idPresidiario', getCaracterizacao(data).idPresidiario);
				
				box.switchArea('.area-grid-alunos', '.area-form-alunos');
			}
		});
	};
	
	getPresidio = function(data) {
		return getCaracterizacao(data).presidio || {};
	};
	
	getCaracterizacao = function(data) {
		return data.caracterizacoes ? data.caracterizacoes[data.caracterizacoes.length - 1] : {};
	};
	
	box.eventClick('#cadastrar-endereco-liberto', function() {
		box.switchArea('.area-form-alunos', '.area-form-alunos-libertos');
	});
	
	box.eventClick('#cancelar-endereco-liberto', function() {
		box.switchArea('.area-form-alunos-libertos', '.area-form-alunos');
	});
	
	box.eventClick('#save-aluno-liberdade', function() {
		box.submitForm('#aluno-liberto-form');
	});

	box.postForm('#aluno-liberto-form', {
		success : function(data) {
			window.location.href = context + "alunos-comuns/" + box.get('#id-liberto');
		}
	});
	
});
