package page_objects_pattern;

import static core.DriverFactory.getDriver;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import core.BaseTest;
import core.DSL;
import core.DriverFactory;

/**
 * 
 * @author paulojunior
 * 
 * Use of parameterizable tests. DDT (Data-Driven Test)
 *
 */
@RunWith(value = Parameterized.class)
public class RegistrationRulesTest extends BaseTest {
	
	private DSL dsl;
	private TrainingCampPage page;
	
	// parameters of test
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String response_msg;

	@Before
	public void inicializa(){
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new TrainingCampPage();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{ "", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio" },
			{ "Paulo", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio" },
			{ "Paulo", "Júnior", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio" },
			{ "Paulo", "Júnior", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?" },
			{ "Paulo", "Júnior", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?" },
		});
	}
	
	@Test
	public void mustValidateRules(){
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if (sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} else if (sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		if (comidas.contains("Carne")) page.setComidaCarne();
		if (comidas.contains("Pizza")) page.setComidaPizza();
		if (comidas.contains("Vegetariano")) page.setComidaVegetariano();
		page.setEsporte(esportes);
		page.cadastrar();
		System.out.println(response_msg);
		Assert.assertEquals(response_msg, dsl.getTextAndAcceptAlert());
	}

}
