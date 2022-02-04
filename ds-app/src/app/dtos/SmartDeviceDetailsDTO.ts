export interface SmartDeviceDetailsDTO {
  id: string;
  description: string;
  address: string;
  maxEnergyConsumption: number;
  avgEnergyConsumption: number;
  userId: string;
  sensorIds: string[];
  maxValues: number[];
  descriptions: string[];
}
