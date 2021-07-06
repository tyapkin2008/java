package MovenentList;

import java.util.Date;

public class Operation {
    // Дата операции,Референс проводки,Описание операции,Приход,Расход
    private Date date;
    private String reference = "";
    private String description = "";
    private double debet = 0;
    private double credit = 0;
    // Организация
    private String organization = "";

    public Operation(Date date, String reference, String description, double debet, double credit) {
        this.date = date;
        this.reference = reference;
        this.description = description;
        this.debet = debet;
        this.credit = credit;
        this.parseOrganization();
    }
    /*
    Метод получает органзацию из строки описания операции.
    Предполагаем, что блоки описания разделены тремя и более пробелами и блок с организацией - второй
    * */
    private void parseOrganization(){
        if(this.description.length() > 0){
            String[] items = this.description.split("\\s{3,}");
            if(items[1].length() > 0){
                /*
                Берем последний текст после "\" или "/"
                */
                if(items[1].lastIndexOf("\\") > 0){
                    this.organization = items[1].substring(items[1].lastIndexOf("\\") + 1).trim();
                }
                else if(items[1].lastIndexOf("/") > 0){
                    this.organization = items[1].substring(items[1].lastIndexOf("/") + 1).trim();
                }
            }
        }
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDebet() {
        return debet;
    }

    public void setDebet(double debet) {
        this.debet = debet;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
