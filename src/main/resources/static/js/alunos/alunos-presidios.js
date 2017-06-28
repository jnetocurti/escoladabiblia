Sandbox('*', function(box) {
	
	editar = function(id) {
		
		box.post(context + 'alunos-presidios/editar', id.toString(), {
			success : function(data) {
				
				box.set('#id', data.id);
				box.set('#nome', data.nome);
				box.check('#batizado', data.batizado);
				box.set('#observacao', data.observacao);
				box.set('#nascimento', data.dataNascimento);
				box.check('#possuiBiblia', data.possuiBiblia);
				box.set('#cela', getCaracterizacao(data).cela);
				box.set('#raio', getCaracterizacao(data).raio);
				box.set('#presidio', getPresidio(data).id || '');
				box.checkRadio('input:radio[name=sexo]', data.sexo);
				box.check('#frequentouIgreja', data.frequentouIgreja);
				box.set('#matricula', getCaracterizacao(data).matricula);
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
	
});
