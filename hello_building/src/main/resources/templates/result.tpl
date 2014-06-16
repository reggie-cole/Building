layout 'mainLayout.tpl',content: contents{
	h1('List of Buildings')
	table(class:'table table-bordered table-striped') {
		thead {
		  tr {
			td 'ID'
			td 'Name'
			td 'Size'
		  }
		}
		tbody {
			if (buildings.empty) { tr { td(colspan:'3', 'No Messages' ) } }
				buildings.each { building ->
				tr {
				  td "${building.buildingId}"
				  td "${building.buildingName}"
				  td "${building.size}"
				}
			}
		}

	  }
}