window.onload = window.onresize = function () {
	var view_width = document.getElementsByTagName('html')[0].getBoundingClientRect().width;

var _html = document.getElementsByTagName('html')[0];

if (view_width > 640)
{
	_html.style.fontSize = 640 / 16 + 'px';
}
else
{
	_html.style.fontSize = view_width / 16 + 'px';
	} 
};