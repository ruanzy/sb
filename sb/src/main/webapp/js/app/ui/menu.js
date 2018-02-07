define(['util'], function(util){
	var obj = {
		init: function(){
			util.render('html/sys/menu.html', 'container');
			$('pre code').each(function(i, block) {
				hljs.highlightBlock(block);
			});
			var setting = {
				data: {
					key: {
						name: 'NAME'
					},
					simpleData: {
						enable: true,
						idKey: "ID",
						pIdKey: "PID"
					}
				},
				callback: {
					onClick: function(event, treeId, treeNode) {
						var id = treeNode.ID;
					}
				}
			};
			$.ajax({
				url: 'resource/list',
				type: 'post',
				async: false,
				dataType: 'json',
		        success: function(result){
		        	$.fn.zTree.init($("#menutree"), setting, result);
		        	var zTreeObj = $.fn.zTree.getZTreeObj("menutree");
		        	var node = zTreeObj.getNodeByParam('ID', 7);
		        	zTreeObj.selectNode(node);
				}
			});
		}
	};
	return obj;
});