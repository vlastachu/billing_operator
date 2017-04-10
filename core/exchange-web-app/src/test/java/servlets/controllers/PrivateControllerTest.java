package servlets.controllers;

import beans.CallManager;
import model.Tariff;
import model.entities.Call;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

/**
 * Created by vlastachu on 11/04/2017.
 */
public class PrivateControllerTest {
    @Test
    public void doGet() throws Exception {

    }

    @Test
    public void doPost() throws Exception {

    }

    @Test
    public void getCallsRequestWithoutQuery() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CallManager callManager = mock(CallManager.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getServletPath()).thenReturn("/calls");
        when(request.getParameter("query")).thenReturn(null);
        when(request.getAttribute("query")).thenReturn("");
        when(request.getRequestDispatcher("WEB-INF/private/calls.jsp")).thenReturn(requestDispatcher);

        List<Call> getCallResult = new ArrayList<>();
        getCallResult.add(new Call("347236478", 34));
        getCallResult.add(new Call("809123898", 0));
        when(callManager.getCalls()).thenReturn(getCallResult);

        PrivateController controller = new PrivateController();
        controller.callManager = callManager;
        controller.doGet(request, response);

        verify(request, atLeast(1)).getParameter("query");
        verify(request, times(1)).setAttribute("query", "");
        verify(request, times(1)).setAttribute("calls", getCallResult);
        verify(request, times(1)).setAttribute("tariff", Tariff.getDefaultLocalTariff());
        verify(request, times(1)).getRequestDispatcher("WEB-INF/private/calls.jsp");
    }

    @Test
    public void getCallsRequestWithQuery() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CallManager callManager = mock(CallManager.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getServletPath()).thenReturn("/calls");
        when(request.getParameter("query")).thenReturn("123");
        when(request.getAttribute("query")).thenReturn("123");
        when(request.getRequestDispatcher("WEB-INF/private/calls.jsp")).thenReturn(requestDispatcher);

        List<Call> getCallResult = new ArrayList<>();
        getCallResult.add(new Call("123", 34));
        when(callManager.getCallsByPhoneNumber("123")).thenReturn(getCallResult);

        PrivateController controller = new PrivateController();
        controller.callManager = callManager;
        controller.doGet(request, response);
        verify(request, atLeast(1)).getParameter("query");
        verify(request, times(1)).setAttribute("query", "123");
        verify(request, times(1)).setAttribute("calls", getCallResult);
        verify(request, times(1)).setAttribute("tariff", Tariff.getDefaultLocalTariff());
        verify(request, times(1)).getRequestDispatcher("WEB-INF/private/calls.jsp");
    }

    @Test
    public void getTariffsRequest() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getServletPath()).thenReturn("/tariffs");
        when(request.getRequestDispatcher("WEB-INF/private/tariffs.jsp")).thenReturn(requestDispatcher);

        PrivateController controller = new PrivateController();
        controller.doGet(request, response);
        verify(request, times(1)).setAttribute("price", Tariff.getDefaultLocalTariff().getMoneyPerMinute());
        verify(request, times(1)).getRequestDispatcher("WEB-INF/private/tariffs.jsp");
    }

    @Test
    public void logoutRequest() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getServletPath()).thenReturn("/logout");
        when(request.getSession(false)).thenReturn(session);

        PrivateController controller = new PrivateController();
        controller.doGet(request, response);
        verify(session, times(1)).invalidate();
        verify(response, times(1)).sendRedirect("/ewa-app/calls");
    }
}