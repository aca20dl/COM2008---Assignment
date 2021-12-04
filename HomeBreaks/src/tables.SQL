SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE Addresses (
AdID int NOT NULL AUTO_INCREMENT,
House varchar(255),
Street varchar(255),
place varchar(255),
PostCode varchar(255),
PRIMARY KEY (AdID)
);
CREATE TABLE Pdetails (
PdID int NOT NULL  AUTO_INCREMENT,
Title varchar(255),
Firstname varchar(255),
Surname varchar(255),
Email varchar(255),
Mobile varchar(255),
Password varchar(255),
IsHost int,
IsGuest int,
AdID int,
PRIMARY KEY (PdID),
FOREIGN KEY (AdID) REFERENCES Addresses(AdID) ON DELETE CASCADE
);
CREATE TABLE Guests (
GuestID int NOT NULL AUTO_INCREMENT,
Username varchar(255),
PdID int,
PRIMARY KEY (GuestID),
FOREIGN KEY(PdID) REFERENCES Pdetails(PdID) ON DELETE CASCADE
);
CREATE TABLE Hosts (
HostID int NOT NULL AUTO_INCREMENT,
Username varchar(255),
AverageRating DOUBLE(5,2),
IsSuperHost INT,
PdID int,
PRIMARY KEY (HostID),
FOREIGN KEY(PdID) REFERENCES Pdetails(PdID) ON DELETE CASCADE
);

CREATE TABLE Reviews (
ReviewID INT NOT NULL AUTO_INCREMENT,
Satisfaction TEXT,
Cleanliness INT,
Communication INT,
CheckIn INT,
Accuracy INT,
Location INT,
Value INT,
GuestID INT,
PropertyID INT,
PRIMARY KEY (ReviewID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE,
FOREIGN KEY (GuestID) REFERENCES Guests(GuestID) ON DELETE CASCADE
);
CREATE TABLE Bookings (
BookingID INT NOT NULL AUTO_INCREMENT,
StartDate DATE,
EndDate DATE,
NumNights INT,
PricePerNight DOUBLE(6,2),
ServiceCharge DOUBLE(6,2),
CleaningCharge DOUBLE(6,2),
TotalCharge DOUBLE(10,2),
GuestID INT,
PropertyID INT,
IsAccepted INT,
PRIMARY KEY (BookingID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE,
FOREIGN KEY (GuestID) REFERENCES Guests(GuestID) ON DELETE CASCADE
);
CREATE TABLE Properties (
PropertyID INT NOT NULL AUTO_INCREMENT,
Name VARCHAR(30),
Description TEXT,
GenLocation VARCHAR(50),
Breakfast INT,
MaxGuest INT,
NumBeds INT,
NumBedrooms INT,
NumBathrooms INT,
AverageRating DOUBLE(6,2),
HostID INT,
AdID INT,
PRIMARY KEY (PropertyID),
FOREIGN KEY (HostID) REFERENCES Hosts(HostID) ON DELETE CASCADE,
FOREIGN KEY (AdID) REFERENCES Addresses(AdID) ON DELETE CASCADE
);
CREATE TABLE Sleeping (
SleepingID INT NOT NULL AUTO_INCREMENT,
HasBedLinen INT,
HasTowels INT,
TotalBedrooms INT,
TotalBeds INT,
TotalSleepers INT,
PropertyID INT,
PRIMARY KEY (SleepingID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);
CREATE TABLE Bathing (
BathingID INT NOT NULL AUTO_INCREMENT,
HasHairdryer INT,
HasShampoo INT,
HasToiletPaper INT,
TotalBathrooms INT,
PropertyID INT,
PRIMARY KEY (BathingID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);
CREATE TABLE Bedrooms (
BedroomID INT NOT NULL AUTO_INCREMENT,
BedOne varchar(30),
BedTwo varchar(30),
NumBeds INT,
NumSleepers INT,
SleepingID INT,
PRIMARY KEY (BedroomID),
FOREIGN KEY (SleepingID) REFERENCES Sleeping(SleepingID) ON DELETE CASCADE
);
CREATE TABLE Bathrooms (
BathroomID INT NOT NULL AUTO_INCREMENT,
HasToilet INT,
HasBath INT,
HasShower INT,
IsShared INT,
BathingID INT,
PRIMARY KEY (BathroomID),
FOREIGN KEY (BathingID) REFERENCES Bathing(BathingID) ON DELETE CASCADE
);
CREATE TABLE Kitchen (
KitchenID INT NOT NULL AUTO_INCREMENT,
HasFridge INT,
HasMicrowave INT,
HasOven INT,
HasStove INT,
HasDishwasher INT,
HasTableware INT,
HasCookware INT,
HasBasicProv INT,
PropertyID INT,
PRIMARY KEY (KitchenID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);
CREATE TABLE Living (
LivingID INT NOT NULL AUTO_INCREMENT,
HasWifi INT,
HasTV INT,
HasSat INT,
HasStreaming INT,
HasDVDPlayer INT,
HasBoardGames INT,
PropertyID INT,
PRIMARY KEY (LivingID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);
CREATE TABLE Outdoor (
OutdoorID INT NOT NULL AUTO_INCREMENT,
HasFreeParking INT,
HasRoadParking INT,
HasPaidCarPark INT,
HasPatio INT,
HasBarbeque INT,
PropertyID INT,
PRIMARY KEY (OutdoorID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);
CREATE TABLE Utility (
UtilityID INT NOT NULL AUTO_INCREMENT,
HasHeating INT,
HasWashingMachine INT,
HasDryingMachine INT,
HasFireExtinguisher INT,
HasSmokeAlarm INT,
HasFirstAidKit INT,
PropertyID INT,
PRIMARY KEY (UtilityID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);
CREATE TABLE ChargeBands (
ChargeBandID INT NOT NULL AUTO_INCREMENT,
StartDate DATE,
EndDate DATE,
PricePerNight DOUBLE(6,2),
ServiceCharge DOUBLE(6,2),
CleaningCharge DOUBLE(6,2),
PropertyID INT,
PRIMARY KEY (ChargeBandID),
FOREIGN KEY (PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=1;