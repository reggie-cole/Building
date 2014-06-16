layout 'mainLayout.tpl',content: contents{
	h1('List of Buildings')
	table(class:'table table-bordered table-striped table-hover') {
		thead {
		  tr {
			td 'ID'
			td 'Name'
			td '# floors'
			td '# rooms'
			td 'Edit'
			td 'Delete'
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
				  td { 	a(role:'button',href:"/delete/${building.buildingId}","${building.buildingId}") {
					  	span(class:"glyphicon glyphicon-remove")
					} }
					 
				}
			}
		}

	  }
		a(role:'button',href:'/save',class:"btn btn-info","Save Changes")
}