html{
	head{
		title(' Create Building');
		link(rel:'stylesheet', href:'//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css')
		link(rel:'stylesheet', href:'//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css')
		
	}
	body
	{
		
		div(class:'container') {
			div(class:'navbar') {
			  div(class:'navbar-inner') {
				ul(class:'nav') {
				  li {
					a(href:'/') {
					  yield 'Building Creator'
					}
				  }
				  li {
					  a(href:'/list') {
						  yield 'Building List'
					  }
				  }
				}
			  }
			}
		h1('Building creator')
		br()
		div { content() }
		script(src:'//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js')
  }
  }
}