Sandbox.modules.bootgrid = function(box) {
	
	box.bootgridAppended = function(grid, rows, configs) {

		rows = _.isArray(rows) ? rows : [];
		configs = _.isObject(configs) ? configs : {};
		
		_.defaults( configs, { navigation : 2 } );
		
		boot(grid, configs).bootgrid("clear").bootgrid("append" , rows);
	};

	box.bootgridPagination = function(grid, configs) {
		
		configs = _.isObject(configs) ? configs : {};
		
		boot(grid, configs);
	};
	
	box.smallGridButton = function(column, row, clazz, icon) {
		
		return "<button type=\"button\" class=\"btn btn-xs btn-default " + clazz + "\" data-row-id=\"" + row.id + "\"><span class=\"fa " + icon + "\"></span></button> ";
	};

	boot = function(grid, configs) {
		
		setDefaults(configs);
		
		return $(grid).bootgrid({

			navigation : configs.navigation,
			
			columnSelection : configs.columnSelection,
			
			rowCount : configs.rowCount,
			
			searchSettings : configs.searchSettings,
			
			formatters : configs.formatters

		}).on("loaded.rs.jquery.bootgrid", function() {
			
			configs.callbacks();
		});
	};
	
	setDefaults = function(configs) {
		
		_.defaults( configs, { navigation : 3 } );
		
		_.defaults( configs, { columnSelection : false } );
		
		_.defaults( configs, { rowCount : [ 5, 10, 15 ] } );
		
		_.defaults( configs, { searchSettings : { delay : 100, characters : 3 } } );
		
		_.defaults( configs, { formatters : {} } );
		 
		_.defaults( configs, { callbacks : new Function() } );
	};

};