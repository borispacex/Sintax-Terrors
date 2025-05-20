import { Team, TeamTable } from '../interfaces/team.interface';

export class TeamTableBuilder {
  public static build(items: Team[]): TeamTable[] {
    return items.map(item => ({
      id: item.id,
      name: item.name,
      description: item.description,
      isActive: item.isActive ? 'Active' : 'Inactive',
      projectManager: item.projectManager,
      manager:
        item.projectManager?.firstName + ' ' + item.projectManager?.lastName,
      team: item,
    }));
  }
}
