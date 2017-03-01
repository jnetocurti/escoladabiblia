Sandbox.modules.bootgrid = function(box) {

	box.bootgrid = function(grid) {

		$(grid).bootgrid({

			columnSelection : false,
			rowCount : [ 5, 10, 15 ],

			searchSettings : {
				delay : 100,
				characters : 3
			}

		});
	};

};