Sandbox('*', function(box) {

	cadastrarHistoricoAtividades = function(id) {
		
		box.resetForm('#historico-atividades-form');
		
		box.post(context + 'atividades-estudo/editar-historicos', id.toString(), {
			success : function(data) {
				
				box.set('#id-aluno-historico', data.idAluno);
				box.text('#nome-aluno-historico', data.nomeAluno);
				
				loadGridHistoricoAtividades(data);
				
				box.switchArea('.area-grid-alunos', '.area-cadastro-historico-atividades');
			}
		});
	};
	
	loadGridHistoricoAtividades = function(data) {
		
		box.bootgridAppended('#historico-atividades', data.historico,
			{
				formatters : {
					"material" : function(column, row) {
						return row.material.nome;
					},
					"certificado" : function(column, row) {
						return _.isNull(row.certificado) ? '' : 'Sim';
					},
					"biblia" : function(column, row) {
						return _.isNull(row.biblia) ? '' : 'Sim';
					},
					"commands" : function(column, row) {
						return box.smallGridButton(column, row, "command-visualizar-historico", "fa-expand");
					}
				},
				callbacks : function() {
					
					box.eventClick('.command-visualizar-historico', function() {
						visualizarAtividade($(this).data("row-id"));
					});
				}
			}
		);
	};
	
	box.eventClick('#save-historico-atividades', function() {
		box.submitForm('#historico-atividades-form');
	});
	
	box.postForm('#historico-atividades-form', {
		success : function(data) {
			box.showMsg({type: 'SUCCESS', message: 'Histórico cadastrado com sucesso!'});
			loadGridHistoricoAtividades(data);
		}
	});
	
});
