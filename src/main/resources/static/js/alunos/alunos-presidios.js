Sandbox('*', function(box) {
	
	box.eventClick('.novo-aluno', function() {
		box.resetForm('#aluno-form');
		box.switchArea('.area-grid-alunos', '.area-form-alunos');
	});
	
	box.eventClick('.cancelar', function() {
		box.switchArea('.area-form-alunos,.area-atualizacao-atividades', '.area-grid-alunos');
	});
	
	box.eventClick('#save-aluno', function() {
		box.submitForm('#aluno-form');
	});

	box.postForm('#aluno-form', {
		success : function(data) {
			box.showMsg(data);
			box.switchArea('.area-form-alunos', '.area-grid-alunos');
			$('.grid-alunos').bootgrid('reload');
		}
	});
	
	box.bootgridPagination('.grid-alunos',
		{
			formatters: {
				"commands": function(column, row) {
					return box.smallGridButton(column, row, "command-activities", "fa-graduation-cap")
						 + box.smallGridButton(column, row, "command-edit", "fa-pencil")
						 + box.smallGridButton(column, row, "command-delete", "fa-trash-o");
				}
			},
			
			callbacks : function() {
				
				box.eventClick('.command-activities', function() {
					gerenciarAtividadesEstudo($(this).data("row-id"));
				});
				
				box.eventClick('.command-edit', function() {
					editar($(this).data("row-id"));
				});
				
				box.eventClick('.command-delete', function() {
					alert($(this).data("row-id"));
				});
			}
		}
	);
	
	gerenciarAtividadesEstudo = function(id) {
		
		box.post(context + 'atividades-estudo/editar-atividades', id.toString(), {
			success : function(data) {
				
				box.set('#id-aluno', data.aluno.id);
				box.text('#nome-aluno', data.aluno.nome);
				box.set('#id-postagem', data.postagem.id);
				box.set('#data-proximo-envio', data.postagem.dataPrevistaEnvio);
				
				loadComboMateriais(data);
				
				loadGridAtividadesEstudo(data);
				
				box.switchArea('.area-grid-alunos', '.area-atualizacao-atividades');
			}
		});
	};
	
	loadComboMateriais = function(data) {
		
		var $materiais = $('#materiais-disponiveis');
		
		box.clearSelect($materiais);
		
		$(data.materiais).each(function() {
			box.addOption($materiais, this.id, this.nome);
		});
	};
	
	loadGridAtividadesEstudo = function(data) {
		
		box.bootgridAppended('#atividades-estudo', data.aluno.atividadesEstudo, 
			{
				formatters : {
					"material" : function(column, row) {
						return row.material.nome;
					},
					"commands" : function(column, row) {
						return box.smallGridButton(column, row, "command-edit", "fa-pencil");
					}
				},
				callbacks : function() {
					
				}
			}
		);
	};
	
	box.eventClick('#add-atividade-estudo', function() {
		box.submitForm('#atividades-estudos-form');
	});
	
	box.postForm('#atividades-estudos-form', {
		resetForm : false,
		success : function(data) {
			
			loadComboMateriais(data);
			
			loadGridAtividadesEstudo(data);
		}
	});
	
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
