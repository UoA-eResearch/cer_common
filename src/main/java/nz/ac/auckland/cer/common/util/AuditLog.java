package nz.ac.auckland.cer.common.util;

import javax.servlet.http.HttpServletRequest;

public class AuditLog {

    public String createAuditLogMessage(
            HttpServletRequest req,
            String message) {

        StringBuffer msg = new StringBuffer();
        msg.append("method=").append(req.getMethod()).append(" ").append("path=").append(req.getPathInfo());
        if (req.getQueryString() != null) {
            msg.append("?").append(req.getQueryString());
        }
        msg.append(" msg='").append(message).append("'");
        return msg.toString();
    }
}
