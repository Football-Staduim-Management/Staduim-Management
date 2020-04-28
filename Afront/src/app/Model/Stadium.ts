import { GeoPoint } from './SearchInfos'
import { User } from './user'

export class stadium{
    id : number
    name : string
    position : GeoPoint
    userManager : User
    location : String
    relativePos : number
}