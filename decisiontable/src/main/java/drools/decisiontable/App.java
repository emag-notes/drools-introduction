package drools.decisiontable;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class App {

  public static void main(String[] args) {
    KieServices services = KieServices.Factory.get();
    KieContainer container = services.getKieClasspathContainer();
    KieSession session = container.newKieSession("DecisionTableKS");

    KieRuntimeLogger logger = services.getLoggers().newFileLogger(session, "./decisiontable");

    Driver driver = new Driver(
      /* name */                    "Mr. John Doe",
      /* age  */                    30,
      /* Number of prior claims */  0,
      /* Location risk profile*/    LocationRiskProfile.LOW.name()
    );

    Policy policy = new Policy(
      /* Policy type applying for */ PolicyType.COMPREHENSIVE.name()
    );

    session.insert(driver);
    session.insert(policy);

    session.fireAllRules();

    System.out.println("########################################");
    System.out.println("BASE PRICE IS: " + policy.getBasePrice());
    System.out.println("DISCOUNT IS: " + policy.getDiscountPercent());

    logger.close();
  }
  
}
