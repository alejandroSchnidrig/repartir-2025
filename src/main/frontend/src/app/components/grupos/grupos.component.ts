import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Grupo } from '../../model/grupo'
import { GrupoService } from '../../services/grupo.service';
import { GastoNuevoComponent } from '../gasto-nuevo/gasto-nuevo.component';
import { GrupoNuevoComponent } from '../grupo-nuevo/grupo-nuevo.component';

@Component({
  selector: 'app-grupos',
  templateUrl: './grupos.component.html',
  styleUrls: ['./grupos.component.css'],
  providers: [GrupoService]
})
export class GruposComponent implements OnInit, AfterViewInit {

  lista: Grupo[] = [];

  @ViewChild(GrupoNuevoComponent) grupoNuevo!: GrupoNuevoComponent;

  @ViewChild(GastoNuevoComponent) gastoNuevo!: GastoNuevoComponent;

  constructor(private grupoService: GrupoService, private messageService: MessageService) {

    this.cargar();
  }

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {

    this.grupoNuevo.guardadoEvent.subscribe(() => this.cargar());
    this.gastoNuevo.guardadoEvent.subscribe(() => this.cargar());
  }

  private cargar(): void {

    this.grupoService.listar().subscribe(
      lista => this.lista = lista,
      error => this.listadoFallido(error)
    );
  }

  crear(): void {

    this.grupoNuevo.iniciar();
  }

  agregarGasto(grupo: Grupo): void {

    this.gastoNuevo.iniciarPara(grupo);
  }

  eliminarGrupo(grupo: Grupo): void {
    if (grupo.id != null) {
      this.grupoService.eliminar(grupo.id).subscribe({
        next: () => this.eliminadoExitoso(grupo),
        error: error => this.eliminadoFallido(error)
      });
    }
  }
 
  private listadoFallido(error: any): void {

    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: error.mensaje,
    });
  }

  private eliminadoExitoso(grupo: Grupo): void {
    this.messageService.add({
      severity: 'success',
      summary: 'Éxito',
      detail: `Grupo '${grupo.nombre}' eliminado correctamente.`,
    });
    this.cargar();
  }

  private eliminadoFallido(error: any): void {
    this.messageService.add({
      severity: 'error',
      summary: 'Error al eliminar',
      detail: error?.mensaje || 'No se pudo eliminar el grupo.',
    });
  }
}