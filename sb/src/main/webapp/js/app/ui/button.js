define([ 'util' ], function(util) {
	var obj = {
		init : function() {
			util.render('html/button.html', 'container');
			$('pre code').each(function(i, block) {
				hljs.highlightBlock(block);
			});
		}
	};
	return obj;
});