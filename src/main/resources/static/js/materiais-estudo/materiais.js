Sandbox('*', function(box) {
	
	$("#sortable").sortable();
	
	box.eventClick('#btn-novo-material', function() {
		box.switchArea('.area-lista-materiais,.acoes-materiais', '.area-form-materiais');
	});
	
	box.eventClick('.cancelar', function() {
		location.reload();
	});
	
	box.eventClick('#btn-save-material', function() {
		box.submitForm('#materiais-form');
	});
	
	box.postForm('#materiais-form', {
		success : function(data) {
			box.showMsg(data);
		}
	});
	
	box.eventClick('#btn-salvar-todos', function() {
		var materiais = getListMateriais();
		box.post(context + 'materiais-estudo/salvar-todos', JSON.stringify(materiais), {
			success : function(data) {
				box.showMsg(data);
			}
		});
	});

	getListMateriais = function() {

		var rows = $('#sortable').children(), materiais = [];

		_.each(rows, function(element, index) {
			materiais.push(createMaterial(element, index));
		});

		return materiais;
	};

	createMaterial = function(element, index) {

		var row = $(element), material = {};

		material.numeroOrdem = index + 1;
		material.id = row.find("#id-material").val();
		material.nome = row.find("#nome-material").val();
		material.ativo = row.find("#ativo").is(":checked");
		material.tipoEnvelope = row.find("#tipo-envelope").val();
		material.corrigible = row.find("#corrigible").is(":checked");

		return material;
	};

});