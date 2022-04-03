package com.safra.open.cashless.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ResponseUtils {

    /**
     * Seta a resposta de um filtro de acordo com o status/mensagem fornecida.
     *
     * @param response Resposta à ser alterada pelo método.
     * @param message Mensagem da resposta.
     * @param status Status da resposta.
     * @throws IOException Caso ocorra a biblioteca jackson não consiga escrever
     *                     os dados repassados para a resposta.
     */
    public static void setResponseError(HttpServletResponse response, String message, int status) throws IOException {
        response.setStatus(status);
        final Map<String, String> error = new HashMap<>();
        error.put("error_message", message);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

}
