package gemcfadyen.drools_experimentation.drivinglicense;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import gemcfadyen.drools_experimentation.DroolsWorkingMemoryHelper;

import org.drools.runtime.StatelessKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

public class DrivingLicenseTest {
	private StatelessKnowledgeSession statelessKnowledgeSession;

	@Before
	public void setupDroolsStatelessSession() {
		statelessKnowledgeSession = DroolsWorkingMemoryHelper.getStatelessWorkingMemoryUsingDroolsFile("drivinglicence.drl");
	}

	@Test
	public void shouldReturnEligibleWhenApplicantIsOver18() {
		Applicant adult = new Applicant("Fred", 25);
		statelessKnowledgeSession.execute(adult);

		assertThat(adult.isEligible(), is(true));
	}

	@Test
	public void shouldReturnNotEligibleWhenApplicantIsUnder18() {

	}

}
