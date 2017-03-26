Sandbox.modules.bootgrid = function(box) {

	box.bootgrid = function(grid, configs) {
		
		configs = configs || {};
		
		initConfigs(configs);

		$(grid).bootgrid({

			columnSelection : configs.columnSelection,
			
			rowCount : configs.rowCount,
			
			searchSettings : configs.searchSettings,
			
			formatters : configs.formatters

		}).on("loaded.rs.jquery.bootgrid", function() {
			
			configs.callbacks();
		});
	};
	
	box.gridEditButton = function(column, row) {
		
		return " <button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-pencil\"></span></button>";
	};
	
	box.gridDeleteButton = function(column, row) {
		
		return " <button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-trash-o\"></span></button>";
	};

	initConfigs = function(configs) {
		
		configs.columnSelection = configs.columnSelection || false;
		
		configs.rowCount = configs.rowCount || [ 5, 10, 15 ];
		
		configs.searchSettings = configs.searchSettings || { delay : 100, characters : 3 };
		
		configs.formatters = configs.formatters || {};
		
		configs.callbacks = configs.callbacks || new Function();
		
	};

};