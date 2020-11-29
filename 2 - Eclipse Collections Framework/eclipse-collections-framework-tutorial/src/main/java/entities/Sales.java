package entities;

import java.util.Date;
import java.util.Objects;

public class Sales {
    private String region;
    private String country;
    private String itemType;
    private String salesChannel;
    private String orderPriority;
    private Date orderDate;
    private String orderId;
    private Date shipDate;
    private int unitsSold;
    private double unitPrice;
    private double unitCost;
    private double totalRevenue;
    private double totalCost;
    private double totalProfit;
    


    public Sales() {
    }

    public Sales(String region, String country, String itemType, String salesChannel, String orderPriority, Date orderDate, String orderId, Date shipDate, int unitsSold, double unitPrice, double unitCost, double totalRevenue, double totalCost, double totalProfit) {
        this.region = region;
        this.country = country;
        this.itemType = itemType;
        this.salesChannel = salesChannel;
        this.orderPriority = orderPriority;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.shipDate = shipDate;
        this.unitsSold = unitsSold;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.totalRevenue = totalRevenue;
        this.totalCost = totalCost;
        this.totalProfit = totalProfit;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getSalesChannel() {
        return this.salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public String getOrderPriority() {
        return this.orderPriority;
    }

    public void setOrderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getShipDate() {
        return this.shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public int getUnitsSold() {
        return this.unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitCost() {
        return this.unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalProfit() {
        return this.totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Sales region(String region) {
        this.region = region;
        return this;
    }

    public Sales country(String country) {
        this.country = country;
        return this;
    }

    public Sales itemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public Sales salesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
        return this;
    }

    public Sales orderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
        return this;
    }

    public Sales orderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Sales orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Sales shipDate(Date shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public Sales unitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
        return this;
    }

    public Sales unitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Sales unitCost(double unitCost) {
        this.unitCost = unitCost;
        return this;
    }

    public Sales totalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
        return this;
    }

    public Sales totalCost(double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public Sales totalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Sales)) {
            return false;
        }
        Sales sales = (Sales) o;
        return Objects.equals(region, sales.region) && Objects.equals(country, sales.country) && Objects.equals(itemType, sales.itemType) && Objects.equals(salesChannel, sales.salesChannel) && Objects.equals(orderPriority, sales.orderPriority) && Objects.equals(orderDate, sales.orderDate) && Objects.equals(orderId, sales.orderId) && Objects.equals(shipDate, sales.shipDate) && unitsSold == sales.unitsSold && unitPrice == sales.unitPrice && unitCost == sales.unitCost && totalRevenue == sales.totalRevenue && totalCost == sales.totalCost && totalProfit == sales.totalProfit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, country, itemType, salesChannel, orderPriority, orderDate, orderId, shipDate, unitsSold, unitPrice, unitCost, totalRevenue, totalCost, totalProfit);
    }

    @Override
    public String toString() {
        return "{" +
            " region='" + getRegion() + "'" +
            ", country='" + getCountry() + "'" +
            ", itemType='" + getItemType() + "'" +
            ", salesChannel='" + getSalesChannel() + "'" +
            ", orderPriority='" + getOrderPriority() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", shipDate='" + getShipDate() + "'" +
            ", unitsSold='" + getUnitsSold() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            ", unitCost='" + getUnitCost() + "'" +
            ", totalRevenue='" + getTotalRevenue() + "'" +
            ", totalCost='" + getTotalCost() + "'" +
            ", totalProfit='" + getTotalProfit() + "'" +
            "}";
    }

   
}
