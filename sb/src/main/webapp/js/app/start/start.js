define(['util'], function(util){
	var obj = {
		init: function(){
			util.render('html/start.html', 'container');
			$("#myModal").on("click", function() {
				var html = util.tpl('html/remote2.html', {username : 'ruanzy'});
				//$.alert('aaa', function(){alert('哈哈');});
				/**$.confirm('aaa', function(v){
					if(v == 'yes'){alert('哈哈');}
					else{alert('呵呵');}
				});**/
				var d = $.dialog({
					title: '下载试用',
					width: 400,
					content: html,
					modal: true,
					buttons: {
						"确定": function() {
				          alert( "Create" );
				        },
				        '取消': function() {
				          d.dialog( "close" );
				        }
				      },
				});
			});
			$("#kkk").on("click", function() {
				$.ajax({
					url: './user/projects',
					type: 'post',
					dataType: 'json',
					success: function(result){
						var ret = JSON.stringify(result, null, 4);
						$("#ret").text(ret);
					}
				});
			});
		}
	};
	return obj;
});