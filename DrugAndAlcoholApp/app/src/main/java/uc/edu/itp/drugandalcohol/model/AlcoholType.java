package uc.edu.itp.drugandalcohol.model;

/**
 * This class represents the different types of drinks the user
 * might consume
 */
public class AlcoholType
{
    // standard drink value
    // e.g. 1 midi = 1.1 standard drinks
    //      1 scooner = 1.6 standard drinks
    double[] beerType = {1.1, 1.6, 1.4, 1.4};
    double[] wineType = {1.4, 1.6, 1.4, 8};
    double[] spiritsType = {1.2, 1, 1.5, 1.6};

    public double getDrinkValue(int type, int index)
    {
        double drinkValue = 0;

        switch (type)
        {
            // beer type
            case 0:
                drinkValue = beerType[index];
                break;
            // wine type
            case 1:
                drinkValue = wineType[index];
                break;
            case 2:
                drinkValue = spiritsType[index];
                break;
        }

        return drinkValue;
    }

}
