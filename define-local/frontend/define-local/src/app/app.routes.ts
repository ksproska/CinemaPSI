import { Routes } from '@angular/router';
import {DefineRepertoireComponent} from "./components/define-repertoire/define-repertoire.component";

export const routes: Routes = [
  {path:'', component: DefineRepertoireComponent},
  {path:'definicja-repertuaru', component: DefineRepertoireComponent},
];
