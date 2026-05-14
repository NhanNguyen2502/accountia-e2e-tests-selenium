package no.genie.accountiae2etestsselenium.elements;

import org.openqa.selenium.By;

public class DashboardElements {
    public static By dashboardTitle = By.xpath("//h1[contains(@class, 'dashboard-title')]");
    public static By welcomeMessage = By.xpath("//div[contains(@class, 'welcome-message')]");
    public static By userProfileIcon = By.xpath("//button[contains(@class, 'user-profile')]");
    public static By logoutButton = By.xpath("//button[contains(text(), 'Logout')]");
    public static By sidebarMenu = By.xpath("//nav[contains(@class, 'sidebar')]");
    public static By dashboardLink = By.xpath("//a[contains(@href, '/dashboard')]");
    public static By companyListLink = By.xpath("//a[contains(@href, '/companies')]");
    public static By settingsLink = By.xpath("//a[contains(@href, '/settings')]");
    public static By notificationsIcon = By.xpath("//button[contains(@class, 'notifications')]");
    public static By notificationBadge = By.xpath("//span[contains(@class, 'badge')]");
}