package servlets.controllers;

import beans.AccountManager;
import beans.CallManager;
import entities.User;
import model.Account;
import model.Tariff;
import model.entities.Call;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PrivateController", urlPatterns = {"/calls", "/accounts", "/tariffs", "/logout" })
public class PrivateController extends HttpServlet {

    @EJB
    CallManager callManager;

    @EJB
    AccountManager accountManager;

    public PrivateController() {
        super();
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("11request = [" + request + "], response = [" + response + "]");
        // String userId = request.getUserPrincipal().getName();
        String phoneNumber;
        Integer money;
        switch (request.getServletPath()) {
            case "/accounts":
                try {
                    Integer.valueOf(request.getParameter("phoneNumber")); // to put exception
                    phoneNumber = request.getParameter("phoneNumber");
                    money = Integer.valueOf(request.getParameter("money"));
                    if (request.getParameter("archiveAccount") != null) {
                        accountManager.archiveAccountIfExist(new Account(phoneNumber, money));
                        response.sendRedirect("/ewa-app/accounts");
                        break;
                    } else if (request.getParameter("addMoney") != null) {
                        Integer moneyAdd = Integer.valueOf(request.getParameter("money_add"));
                        accountManager.addMoney(new Account(phoneNumber, money), moneyAdd);
                        response.sendRedirect("/ewa-app/accounts");
                        break;
                    }
                } catch (AccountManager.NoSuchAccountException e) {
                    response.sendRedirect("/ewa-app/accounts?error=NoSuchAccountException");
                    break;
                } catch (AccountManager.NotEnoughMoneyException e) {
                    response.sendRedirect("/ewa-app/accounts?error=NotEnoughMoneyException");
                    break;
                } catch (NumberFormatException e) {
                    response.sendRedirect("/ewa-app/accounts?error=NumberFormatException");
                    break;
                }
                accountManager.addAccount(new Account(phoneNumber, money));
                response.sendRedirect("/ewa-app/accounts");
                break;
            case "/calls":
                try {
                    phoneNumber = request.getParameter("phoneNumber");
                    String[] minsec = request.getParameter("duration").split(":");
                    if (minsec.length < 2) {
                        throw new NumberFormatException();
                    }
                    Integer duration = Integer.valueOf(minsec[0]) * 60 + Integer.valueOf(minsec[1]);
                    System.out.println("request = [" + request + "], response = [" + response + "]");
                    callManager.addCall(new Call(phoneNumber, duration));
                } catch (AccountManager.NoSuchAccountException e) {
                    response.sendRedirect("/ewa-app/calls?error=NoSuchAccountException");
                    break;
                } catch (AccountManager.NotEnoughMoneyException e) {
                    response.sendRedirect("/ewa-app/calls?error=NotEnoughMoneyException");
                    break;
                } catch (NumberFormatException e) {
                    response.sendRedirect("/ewa-app/calls?error=NumberFormatException");
                    break;
                }
                response.sendRedirect("/ewa-app/calls");
            default:
                break;
        }
    }

    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        User user;
        switch (request.getServletPath()) {
            case "/calls":
                List<Call> calls;
                if (request.getParameter("query") != null) {
                    request.setAttribute("query", request.getParameter("query"));
                    calls = callManager.getCallsByPhoneNumber(request.getParameter("query"));
                } else {
                    request.setAttribute("query", "");
                    calls = callManager.getCalls();
                }
                if (request.getParameter("error") != null) {
                    request.setAttribute("error", request.getParameter("error"));
                } else {
                    request.setAttribute("error", "");
                }
                // TODO write jsp error output and check
                Tariff tariff = Tariff.getDefaultLocalTariff();
                request.setAttribute("calls", calls);
                request.setAttribute("tariff", tariff);
                request.getRequestDispatcher("WEB-INF/private/calls.jsp").forward(request, response);
                break;
            case "/accounts":
                List<Account> accounts = accountManager.getAll();
                request.setAttribute("accounts", accounts);
                if (request.getParameter("error") != null) {
                    request.setAttribute("error", request.getParameter("error"));
                } else {
                    request.setAttribute("error", "");
                }
                request.getRequestDispatcher("WEB-INF/private/accounts.jsp").forward(request, response);
                break;
            case "/tariffs":
                request.setAttribute("price", Tariff.getDefaultLocalTariff().getMoneyPerMinute());
                request.getRequestDispatcher("WEB-INF/private/tariffs.jsp").forward(request, response);
                break;
            case "/logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("/ewa-app/calls");
                break;
            default:
                break;
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPostRequest(req, resp);
    }
}
