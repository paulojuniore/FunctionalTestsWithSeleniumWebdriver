package page_objects_pattern;

import core.DSL;

public class TrainingCampPage {
	
	private DSL dsl;
	
	public TrainingCampPage() {
		dsl = new DSL();
	}

	public void setNome(String nome) {
		dsl.writeOnTextField("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.writeOnTextField("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino(){
		dsl.clickRadioButton("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino(){
		dsl.clickRadioButton("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		dsl.clickRadioButton("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaPizza(){
		dsl.clickRadioButton("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano(){
		dsl.clickRadioButton("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor) {
		dsl.selectSpinner("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String... valores) {
		for(String valor: valores)
			dsl.selectSpinner("elementosForm:esportes", valor);
	}
	
	public void cadastrar(){
		dsl.clickButton("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro(){
		return dsl.getFieldValue("resultado");
	}
	
	public String obterNomeCadastro(){
		return dsl.getFieldValue("descNome");
	}
	
	public String obterSobrenomeCadastro(){
		return dsl.getFieldValue("descSobrenome");
	}
	
	public String obterSexoCadastro(){
		return dsl.getFieldValue("descSexo");
	}
	
	public String obterComidaCadastro(){
		return dsl.getFieldValue("descComida");
	}
	
	public String obterEscolaridadeCadastro(){
		return dsl.getFieldValue("descEscolaridade");
	}
	
	public String obterEsportesCadastro(){
		return dsl.getFieldValue("descEsportes");
	}
}
