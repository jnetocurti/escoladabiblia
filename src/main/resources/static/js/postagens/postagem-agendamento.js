Sandbox('*', function(box) {
	
	box.eventClick('#agendar', function() {
		box.submitForm('#agendamento-form');
	});

	box.postForm('#agendamento-form', {
		success : function(data) {
			box.showMsg(data);
		}
	});

});
