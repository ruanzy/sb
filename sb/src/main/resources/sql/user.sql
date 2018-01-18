#sql('count')
	select count(1) from users where 1=1
	#if(name)
	and name = #p(name)
	#end
#end

#sql('list')
	select * from users where 1=1
	#if(name)
	and name = #p(name)
	#end
	order by id DESC
#end