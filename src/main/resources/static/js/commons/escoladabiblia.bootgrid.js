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
	
	box.smallGridButton = function(column, row, clazz, icon) {
		return "<button type=\"button\" class=\"btn btn-xs btn-default " + clazz + "\" data-row-id=\"" + row.id + "\"><span class=\"fa " + icon + "\"></span></button> ";
	};

	initConfigs = function(configs) {
		
		configs.columnSelection = configs.columnSelection || false;
		
		configs.rowCount = configs.rowCount || [ 5, 10, 15 ];
		
		configs.searchSettings = configs.searchSettings || { delay : 100, characters : 3 };
		
		configs.formatters = configs.formatters || {};
		
		configs.callbacks = configs.callbacks || new Function();
		
	};

};