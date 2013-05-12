package gemcfadyen.drools_experimentation.globals;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import gemcfadyen.drools_experimentation.globals.GlobalResultObject;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

//Using globals on RHS and LHS of the rules
public class GlobalTest {
	private StatefulKnowledgeSession workingMemory;

	@Before
	public void setup() {
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		ClassPathResource resource = new ClassPathResource("../globals.drl",	this.getClass());
		knowledgeBuilder.add(resource, ResourceType.DRL);

		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();

		knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
		workingMemory = knowledgeBase.newStatefulKnowledgeSession();
	}

	@Test
	public void shouldSuccessfullyChangeAGlobalsValueInTheDrl() {
		GlobalResultObject result = new GlobalResultObject();
		workingMemory.setGlobal("result", result);
		workingMemory.fireAllRules();

		assertThat(result.getMessage(), is("Global message has been set"));

	}

	@Test
	public void shouldSetTheMessageOnTheGlobalIndicatingAFlagHasBeenSet() {
		GlobalResultObject result = new GlobalResultObject();
		result.setIsSet(10);

		workingMemory.setGlobal("result", result);
		workingMemory.insert(result);
		workingMemory.fireAllRules();

		assertThat(result.getMessage(), is("Global has a flag set so no message is returned"));
	}

}