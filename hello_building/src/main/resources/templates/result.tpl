layout 'mainLayout.tpl',content: contents{
	h1('List of Buildings')
	table(class:'table table-bordered table-striped') {
		thead {
		  tr {
			td 'ID'
			td 'Name'
			td '# floors'
			td '# rooms'
			td 'Edit'
		  }
		}
		tbody {
			if (buildings.empty) { tr { td(colspan:'5', 'No Messages' ) } }
				buildings.each { building ->
				tr(class:'clickableRow' , href:'/'){ 
				  td "${building.buildingId}" 
				  td "${building.buildingName}"
				  td "${building.rooms}"
				  td "${building.floorCount}"
				  td { 	a(href:"/${building.buildingId}","${building.buildingId}") {
					  yield 'edit'
					} }
					 
				}
			}
		}

	  }
}