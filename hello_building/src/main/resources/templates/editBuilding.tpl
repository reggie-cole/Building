layout 'mainLayout.tpl',content: contents {
	h1("Edit Building- ${building.buildingName} ID: ${building.buildingId}" )
	br()
	form(action:"/list/${building.buildingId}", method:'post')
	{
		div(class:"form-group"){
			label (for:'buildingName','Building Name:',class:"col-sm-2 control-label")
			input (name:'buildingName',type:'text',value:"$building.buildingName",class:buildingName)
		}
		div(class:"form-group"){
			label (for:'rooms', 'Building Rooms:',class:"col-sm-2 control-label")
			input (name:'rooms', type:"number",value:"${building.rooms}",class:rooms,min:1 )
		}
		div(class:"form-group"){
			label (for:'floorCount', 'Building Floors:',class:"col-sm-2 control-label")
			input (name:'floorCount', type:"number",value:"${building.floorCount}",class:floorCount,min:1 )
		}
		
		br()
		
		br()
		div (class:'form-actions'){
				button(class:"btn btn-info",type:"submit", "Edit")
			}
	}
}