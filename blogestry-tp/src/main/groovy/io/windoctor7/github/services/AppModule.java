package io.windoctor7.github.services;

import java.io.IOException;

import io.windoctor7.github.model.Articulo;
import io.windoctor7.github.services.rest.Blog;
import io.windoctor7.github.services.rest.IBlog;
import io.windoctor7.github.services.rest.Articulos;
import io.windoctor7.github.services.rest.IArticulos;
import io.windoctor7.github.utils.MarkdownConverter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.*;
import org.apache.tapestry5.ioc.annotations.Advise;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;
import org.apache.tapestry5.services.*;
import org.slf4j.Logger;
import org.tynamo.shiro.extension.realm.text.ExtendedPropertiesRealm;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(IBlog.class, Blog.class);
        binder.bind(IArticulos.class, Articulos.class);
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }

    public static void contributeFactoryDefaults(
        MappedConfiguration<String, Object> configuration)
    {
        // The values defined here (as factory default overrides) are themselves
        // overridden with application defaults by DevelopmentModule and QaModule.

        // The application version is primarily useful as it appears in
        // any exception reports (HTML or textual).
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");

        // This is something that should be removed when going to production, but is useful
        // in the early stages of development.
        configuration.override(SymbolConstants.PRODUCTION_MODE, false);
    }

    public static void contributeApplicationDefaults(
        MappedConfiguration<String, Object> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

              // You should change the passphrase immediately; the HMAC passphrase is used to secure
        // the hidden field data stored in forms to encrypt and digitally sign client-side data.
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "change this immediately");

        configuration.add("api","http://127.0.0.1:8081/");

    }

	/**
	 * Use annotation or method naming convention: <code>contributeApplicationDefaults</code>
	 */
	@Contribute(SymbolProvider.class)
	@ApplicationDefaults
	public static void setupEnvironment(MappedConfiguration<String, Object> configuration)
	{
        // Support for jQuery is new in Tapestry 5.4 and will become the only supported
        // option in 5.5.
		configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
		//configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "context:mybootstrap");
	}

    @Contribute(WebSecurityManager.class)
    public static void addRealms(Configuration<Realm> configuration) {
        ExtendedPropertiesRealm realm = new ExtendedPropertiesRealm("classpath:shiro-users.properties");
        configuration.add(realm);
    }


    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     *
     *
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     *
     *
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log)
    {
        return new RequestFilter()
        {
            public boolean service(Request request, Response response, RequestHandler handler)
            throws IOException
            {
                long startTime = System.currentTimeMillis();

                try
                {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info("Request time: {} ms", elapsed);
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    @Contribute(RequestHandler.class)
    public void addTimingFilter(OrderedConfiguration<RequestFilter> configuration,
     @Local
     RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }

    @Advise(serviceInterface = IArticulos.class)
    public static void htmlToMarkdown(MethodAdviceReceiver receiver)
    {
        MethodAdvice advice = new MethodAdvice()
        {
            @Override
            public void advise(MethodInvocation methodInvocation) {
                String methodName = methodInvocation.getMethod().getName();
                if("crear".equals(methodName) || "actualizar".equals(methodName)) {
                    Articulo articulo = (Articulo) methodInvocation.getParameter(0);
                    String html = MarkdownConverter.toHtml(articulo.getMarkdown());
                    articulo.setContenido(html);
                    methodInvocation.setParameter(0, articulo);
                }
                methodInvocation.proceed();
            }
        };

        receiver.adviseAllMethods(advice);
    }

}
