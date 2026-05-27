package xyz.hiveforge.mahouryoku.magic_systems.mahou;

public interface MahouPoolInterface {

    //====================================
    //         Mahou Pool Interface
    //Constructs the Functions and Values
    // to be used by the Player's Codec
    //          and Capability
    //====================================
    boolean GetMahouPool();//Check If the Player Has a Mahou Pool
    void SetMahouPool(boolean Unlocked);//Sets the Players Mahou Pool to True or False
    float MahouPoolValue(); //Value for How much Mahou is within the Pool
    void MaxMahouPoolSize(float MaxMahouPoolSize);//Updates the Mahou Pool Size
    float MahouPoolSize(); //Current Max MahouPool
    void ConsumeFromMahouPool(float mahou); //Takes A Float Value of Mahou From the Mahou Pool Value
    void RegenerateMahouPool(float mahou); //Adds a Float Value of Mahou To The Mahou Pool Value
    float Affinity(); //Affinity Value
    float Eccentricity(); //Eccentricity Value
    void SetAffinity(float Affinity); //Sets the Affinity
    void SetEccentricity(float Eccentricity); //Sets the Eccentricity
}
