
export class GeoPoint {
    alt : number
    lng : number

}

export class SearchInfos {
    zoneCenter: GeoPoint = new GeoPoint()
    zoneRaduis: number
    time: String
    date: String
    location : String
}