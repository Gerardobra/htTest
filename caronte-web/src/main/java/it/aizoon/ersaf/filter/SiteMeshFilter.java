package it.aizoon.ersaf.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.Sm2TagRuleBundle;
import org.springframework.stereotype.Component;

/**
 * @author ivan.morra
 */

@Component
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

  @Override
  protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
    builder.addTagRuleBundle(new Sm2TagRuleBundle())
        .addDecoratorPath("/*", "/WEB-INF/jsp/decorators/default.jsp")
        .addDecoratorPath("/import/*", "/WEB-INF/jsp/decorators/import.jsp")
        .addDecoratorPath("/export/*", "/WEB-INF/jsp/decorators/export.jsp")
        .addDecoratorPath("/admin/*", "/WEB-INF/jsp/decorators/admin.jsp")
        .addDecoratorPath("/vivai/*", "/WEB-INF/jsp/decorators/vivai.jsp")
        .addDecoratorPath("/autorizzazioni/*", "/WEB-INF/jsp/decorators/autorizzazioni.jsp")
        .addDecoratorPath("/controlli/*", "/WEB-INF/jsp/decorators/controlli.jsp")
        .addDecoratorPath("/dashboard", "/WEB-INF/jsp/decorators/principale.jsp")
        .addDecoratorPath("/informazioniProfiloUtente", "/WEB-INF/jsp/decorators/principale.jsp")
        .addDecoratorPath("/privacy", "/WEB-INF/jsp/decorators/principale.jsp")        
        .addDecoratorPath("/modificaProfiloUtente", "/WEB-INF/jsp/decorators/principale.jsp")
        .addExcludedPath("/*/comunicazioni/centroaziendale/dettaglio").addExcludedPath("/*/comunicazioni/centroaziendale/dettaglio/*")
    	.addExcludedPath("/*/comunicazioni/azienda/editAttivitaMateriali").addExcludedPath("/*/comunicazioni/azienda/editAttivitaMateriali/*")
    	.addExcludedPath("/*/campioni/editCampioni").addExcludedPath("/*/campioni/editCampioni/*")
        .addExcludedPath("/*/controllofisico/editOrgNocFisico").addExcludedPath("/*/controllofisico/editOrgNocFisico/*");
    
    
    /*
     * builder.addDecoratorPath("/*", "/default-decorator.html")
     * 
     * // Map decorators to path patterns. .addDecoratorPath("/admin/*",
     * "/another-decorator.html") .addDecoratorPath("/*.special.jsp",
     * "/special-decorator.html") // Map multiple decorators to the a single
     * path. .addDecoratorPaths("/articles/*", "/decorators/article.html",
     * "/decoratos/two-page-layout.html", "/decorators/common.html") // Exclude
     * path from decoration. .addExcludedPath("/javadoc/*")
     * .addExcludedPath("/brochures/*");
     */

    // builder.setMimeTypes("text/html", "application/xhtml+xml",
    // "application/vnd.wap.xhtml+xml")

    /*
     * define custom rules that manipulate tags on a page. These are classes
     * that implement org.sitemesh.content.tagrules.TagRuleBundle.
     */
    // builder.addTagRuleBundles(new CssCompressingBundle(), new
    // LinkRewritingBundle());
  }
}
