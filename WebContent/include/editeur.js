function selectionne_tout() {
	var editeur = document.getElementById('editeur');
	// var deb = editeur.selectionStart;
	// var fin = editeur.selectionEnd;
	editeur.setSelectionRange(0,editeur.value.length);
}

function efface_tout() {
	document.getElementById('editeur').value = "";
	met_a_jour();
}

function met_a_jour() {
	document.getElementById('resultat').innerHTML = document.getElementById('editeur').value;
}

function balise(mon_tag) {
	var editeur = document.getElementById('editeur');
	var deb = editeur.selectionStart;
	var fin = editeur.selectionEnd;
	var nl;
	if ((mon_tag == "ol") || (mon_tag == "ul") || (mon_tag == "table") || (mon_tag == "tr")) nl = "\n";
	else nl = "";
	var txt =
		editeur.value.substring(0,deb)+'<'+mon_tag+'>'+nl
		+editeur.value.substring(deb,fin)+nl+'</'+mon_tag+'>'
		+editeur.value.substring(fin,editeur.length);
	editeur.value = txt;
	deb += 2 + mon_tag.length + nl.length;
	fin += 2 + mon_tag.length + nl.length;
	met_a_jour();
	editeur.setSelectionRange(deb,fin);
}

function balise_simble(mon_tag) {
	var editeur = document.getElementById('editeur');
	var fin = editeur.selectionEnd;
	var txt =
		editeur.value.substring(0,fin)+'<'+mon_tag+'>\n'
		+editeur.value.substring(fin,editeur.length);
	editeur.value = txt;
	fin += 3 + mon_tag.length;
	met_a_jour();
	editeur.setSelectionRange(fin,fin);
}

function tage(mon_tag, element) {
	var editeur = document.getElementById('editeur');
	var deb = editeur.selectionStart;
	var fin = editeur.selectionEnd;
	var txt =
		editeur.value.substring(0,deb)+'<'+mon_tag+' '+element+'="">'
		+editeur.value.substring(deb,fin)+'</'+mon_tag+'>'
		+editeur.value.substring(fin,editeur.length);
	editeur.value = txt;
	deb += 4 + element.length + mon_tag.length;
	met_a_jour();
	editeur.setSelectionRange(deb,deb);
}

function colorise(texte_fond, ma_couleur) {
	var editeur = document.getElementById('editeur');
	var deb = editeur.selectionStart;
	var fin = editeur.selectionEnd;
	if (texte_fond == "texte") var type = "color";
	else if (texte_fond == "fond") var type = "background-color";
	var txt =
		editeur.value.substring(0,deb)+'<span style="'+type+':'+ma_couleur+'">'
		+editeur.value.substring(deb,fin)+'</span>'
		+editeur.value.substring(fin,editeur.length);
	editeur.value = txt;
	deb += 23 + type.length;
	fin += 23 + type.length;
	met_a_jour();
	editeur.setSelectionRange(deb,fin);
}