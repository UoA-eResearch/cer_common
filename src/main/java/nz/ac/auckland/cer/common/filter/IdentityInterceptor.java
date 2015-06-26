package nz.ac.auckland.cer.common.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

public class IdentityInterceptor implements Filter {

    private Resource idResource;
    private Logger log = Logger.getLogger(IdentityInterceptor.class.getName());
    private Logger flog = Logger.getLogger("file." + IdentityInterceptor.class.getName());

    public void doFilter(
            ServletRequest req,
            ServletResponse resp,
            FilterChain filterChain) throws IOException, ServletException {

        try {
            Properties props = new Properties();
            if (this.idResource.exists() && this.idResource.isReadable()) {
                props.load(idResource.getInputStream());
                String admins = (String) props.get("admins");
                String eppn = (String) req.getAttribute("eppn");
                if (admins != null && (admins.equals("*") || (eppn != null && admins.contains(eppn)))) {
                    props.remove("admins");
                    for (Object key : props.keySet()) {
                        req.setAttribute((String) key, props.get(key));
                    }                    
                    this.logIdentityChange((HttpServletRequest) req, props);
                }
            }
        } catch (Exception e) {
            log.error("Unexpected error", e);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    /*
     * Log message 
     */
    private void logIdentityChange(
            HttpServletRequest request,
            Properties props) {

        StringBuffer msg = new StringBuffer();
        msg.append("ID-INTERCEPTION:");
        for (Object key : props.keySet()) {
            msg.append(" " + key + "=\"" + props.get(key) + "\"");
        }  
        log.info(msg.toString());
        flog.info(this.createAuditLogMessage(request, msg.toString()));
    }

    public void init(
            FilterConfig arg0) throws ServletException {

    }

    public void destroy() {

    }

    public void setIdResource(
            Resource idResource) {

        this.idResource = idResource;
    }
    
    private String createAuditLogMessage(
            HttpServletRequest req,
            String message) {

        StringBuffer msg = new StringBuffer();
        msg.append("method=").append(req.getMethod()).append(" ");
        msg.append("path=").append(req.getPathInfo());
        if (req.getQueryString() != null) {
            msg.append("?").append(req.getQueryString());
        }
        msg.append(" msg='").append(message).append("'");
        return msg.toString();
    }


}
