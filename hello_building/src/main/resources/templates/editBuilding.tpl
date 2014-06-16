layout 'mainLayout.tpl',content: contents {
	h1("Edit Building- ${building.buildingName} ID: ${building.buildingId}" )
	br()
	form(action:"/list/${building.buildingId}", method:'post')
	{
		label (for:'rooms', 'Building Rooms:')
		input (name:'rooms', type:'number',value:"${building.rooms}",class:rooms,min:1 )
		br()
		label (for:'floorCount', 'Building Floors:')
		input (name:'floorCount', type:'number',value:"${building.floorCount}",class:floorCount,min:1 )
		br()
		div (class:'form-actions'){
			input(type:'submit',value:'edit')
		}
	}
}