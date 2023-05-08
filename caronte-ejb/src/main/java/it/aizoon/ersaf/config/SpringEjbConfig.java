package it.aizoon.ersaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean;

import it.aizoon.ersaf.business.IAbstractEJB;
import it.aizoon.ersaf.business.IAsyncInvioMailEJB;
import it.aizoon.ersaf.business.IAutenticazioneEJB;
import it.aizoon.ersaf.business.IComunicazioniEJB;
import it.aizoon.ersaf.business.IControlliEJB;
import it.aizoon.ersaf.business.IDecodificheEJB;
import it.aizoon.ersaf.business.IDomandeEJB;
import it.aizoon.ersaf.business.IGeneriSpecieEJB;
import it.aizoon.ersaf.business.IManagerInvioMailEJB;
import it.aizoon.ersaf.business.IOrganismiNociviEJB;
import it.aizoon.ersaf.business.IProtocolloEJB;
import it.aizoon.ersaf.business.IReportEJB;
import it.aizoon.ersaf.business.IRichiesteEJB;
import it.aizoon.ersaf.business.IServiziEJB;
import it.aizoon.ersaf.business.ITraceIEJB;
import it.aizoon.ersaf.business.IUtenteEJB;

@Configuration
// @ComponentScan(basePackages="it.aizoon.ersaf.business")
// @ComponentScan(basePackages="it.aizoon.ersaf.integration")
public class SpringEjbConfig {
  // Attributo mappato nel tag <uri> del modulo ejb di maven-ear-plugin
  private String moduleName = "caronte-ejb";

  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean utenteEJB() {
    return this.getEjbInstance(IUtenteEJB.class, "Utente");
  }

  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean richiesteEJB() {
    return this.getEjbInstance(IRichiesteEJB.class, "Richieste");
  }

  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean autenticazioneEJB() {
    return this.getEjbInstanceGeneric(IAutenticazioneEJB.class, "Autenticazione");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean decodificheEJB() {
    return this.getEjbInstance(IDecodificheEJB.class, "Decodifiche");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean reportEJB() {
    return this.getEjbInstance(IReportEJB.class, "Report");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean generiSpecieEJB() {
    return this.getEjbInstance(IGeneriSpecieEJB.class, "GeneriSpecie");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean serviziEJB() {
    return this.getEjbInstance(IServiziEJB.class, "Servizi");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean controlliEJB() {
    return this.getEjbInstance(IControlliEJB.class, "Controlli");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean domandeEJB() {
    return this.getEjbInstance(IDomandeEJB.class, "Domande");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean comunicazioniEJB() {
    return this.getEjbInstance(IComunicazioniEJB.class, "Comunicazioni");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean managerInvioMailEJB() {
    return this.getEjbInstanceGeneric(IManagerInvioMailEJB.class, "ManagerInvioMail");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean asyncInvioMailEJB() {
    return this.getEjbInstanceGeneric(IAsyncInvioMailEJB.class, "AsyncInvioMail");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean traceEJB() {
    return this.getEjbInstanceGeneric(ITraceIEJB.class, "Trace");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean protocolloEJB() {
    return this.getEjbInstance(IProtocolloEJB.class, "Protocollo");
  }
  
  @Bean
  @Lazy
  public LocalStatelessSessionProxyFactoryBean organismoNocivoEJB() {
    return this.getEjbInstance(IOrganismiNociviEJB.class, "OrganismiNocivi");
  }

  private LocalStatelessSessionProxyFactoryBean getEjbInstance(Class<? extends IAbstractEJB<?, ?>> businessClass, String ejbName) {
    LocalStatelessSessionProxyFactoryBean factory = new LocalStatelessSessionProxyFactoryBean();
    factory.setBusinessInterface(businessClass);
    factory.setJndiName("java:app/" + moduleName + "/" + ejbName);
    factory.setCacheHome(true);
    factory.setLookupHomeOnStartup(false);
    factory.setResourceRef(false);
    return factory;
  }

  private LocalStatelessSessionProxyFactoryBean getEjbInstanceGeneric(Class<?> businessClass, String ejbName) {
    LocalStatelessSessionProxyFactoryBean factory = new LocalStatelessSessionProxyFactoryBean();
    factory.setBusinessInterface(businessClass);
    factory.setJndiName("java:app/" + moduleName + "/" + ejbName);
    factory.setCacheHome(true);
    factory.setLookupHomeOnStartup(false);
    factory.setResourceRef(false);
    return factory;
  }
}
