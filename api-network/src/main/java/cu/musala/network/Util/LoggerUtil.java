package cu.musala.network.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggerUtil {

    public static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    private static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    private static String exceptionMessage(Exception e) {
        switch (e.getClass().getSimpleName()) {
            default:
                return getStackTrace(e);
        }
    }

    public static void exceptionLog(Exception e) {
        logger.error(exceptionMessage(e));
    }

    public static void requestData(HttpServletRequest request) {

        String jsonBody = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonBody = objectMapper.writeValueAsString(request.getParameterMap());
        } catch (Exception e) {
            LoggerUtil.exceptionLog(e);
        }

        logger.info("\"Request\": {\"url\":\"" + request.getRequestURL() + "\", \"method\":\"" + request.getMethod() + "\", \"params\":" + jsonBody + "}");
    }


}
